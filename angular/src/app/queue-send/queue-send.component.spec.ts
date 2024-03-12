import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QueueSendComponent } from './queue-send.component';

describe('QueueSendComponent', () => {
  let component: QueueSendComponent;
  let fixture: ComponentFixture<QueueSendComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QueueSendComponent]
    });
    fixture = TestBed.createComponent(QueueSendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
