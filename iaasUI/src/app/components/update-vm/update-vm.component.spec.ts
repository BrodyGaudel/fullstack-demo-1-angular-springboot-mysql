import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateVmComponent } from './update-vm.component';

describe('UpdateVmComponent', () => {
  let component: UpdateVmComponent;
  let fixture: ComponentFixture<UpdateVmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateVmComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateVmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
