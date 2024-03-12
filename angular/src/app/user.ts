import { SocialUser } from "@abacritt/angularx-social-login";

export class User implements SocialUser {
    provider!: string;
    id!: string;
    email!: string;
    name!: string;
    photoUrl!: string;
    firstName!: string;
    lastName!: string;
    authToken!: string;
    idToken!: string;
    authorizationCode!: string;
    response: any;
    
}

