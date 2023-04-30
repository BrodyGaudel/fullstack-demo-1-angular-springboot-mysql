import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsVmComponent } from './details-vm.component';

describe('DetailsVmComponent', () => {
  let component: DetailsVmComponent;
  let fixture: ComponentFixture<DetailsVmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsVmComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailsVmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
