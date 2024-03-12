package com.empbackend.employee.controller;

import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.interfaces.JWTVerifier;
import com.empbackend.employee.exception.ResourceNotFoundException;
import com.empbackend.employee.model.Employee;
import com.empbackend.employee.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dev/employee-api/")
public class EmployeeController {
    @Autowired
    public EmployeeRepository employeeRepository;
    SecurityController securityController = new SecurityController();

    @GetMapping("/get-all-employees")
    public Page<Employee> getAllEmployees(@RequestHeader("Authorization") String auth, Pageable pageable) {
        if (authorise(auth)) {
            return employeeRepository.findAll(pageable);
        } else {
            return Page.empty();
        }
    }

    @PostMapping("/create-employee")
    public Employee createEmployee(@RequestBody Employee data) {
        return employeeRepository.save(data);
    }

    @DeleteMapping("/delete-employee/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeRepository.deleteById(id);
    }

    @PatchMapping("/update-employee/{id}")
    public void updateEmployee(@PathVariable Integer id, @RequestBody Employee newData) {
        newData.setEmpId(id);
        employeeRepository.save(newData);
    }

    @GetMapping("/get-employee/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist"));
    }

    private boolean authorise(String auth) {
        try {
            String accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IkdxUVphN2FEVUdHOFVNZVFnS2dQbCJ9.eyJpc3MiOiJodHRwczovL2Rldi1sdWNpZmVyLW1vcm5pbmdzdGFyLXB0Yy51cy5hdXRoMC5jb20vIiwic3ViIjoiVlgwSkh0a1Q0TmJra2NyS0pMZXdSUExkSUVYNFIxZFFAY2xpZW50cyIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6NDIwMCIsImlhdCI6MTcwMDY1OTIwOSwiZXhwIjoxNzAwNzQ1NjA5LCJhenAiOiJWWDBKSHRrVDROYmtrY3JLSkxld1JQTGRJRVg0UjFkUSIsImd0eSI6ImNsaWVudC1jcmVkZW50aWFscyJ9.wpcDAjSdU2DrucRlb4O3QIXkGbaG_9PFnLz--mH3NxV5Q-6c0km7DV2ZekaXl_ib-SV-5fVTzBbTITUx4rM-Ag-3Ly9q2S5-Tf-8NophvRhAjBk77vrqZTGYPSAZ68Cphq5rMSCOV2F8lEUTEKEior0OtOFRawrRV27UByoS0GMCKwmRfBgO46LVdomlu5S1HA65HAANgme32zD5lJWaPPLa08max36o4ODOQAc2D3TTUKkfmkufn6rY-xdXaloqMoZ3Tdle5DskZO087WHqyR_Bvu4414EyVirt0hYMlDOipTvrRQ8N1x0_BotXGe4YP_lhMzRwzkpdf-olQy9aiQ";
            System.out.println("Access TOken is fetched from the front end in Employee Controller" + accessToken);
            RSAPublicKey rsaPublicKey = securityController.getPublicKey(accessToken);
            System.out.println("RSAKey is received from Security Controller" + rsaPublicKey);
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, null); // null as there is no private key

            // Build a verifier with specified claims
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("https://dev-lucifer-morningstar-ptc.us.auth0.com/") // You can customize claims
                                                                                     // validation as needed
                    .build();

            // Verify the token
            verifier.verify(accessToken);

            // If verification succeeds, the token is valid
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Token verification failed
        }

    }
}
