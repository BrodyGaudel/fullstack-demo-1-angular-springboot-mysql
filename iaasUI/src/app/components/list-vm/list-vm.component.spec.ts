import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListVmComponent } from './list-vm.component';

describe('ListVmComponent', () => {
  let component: ListVmComponent;
  let fixture: ComponentFixture<ListVmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListVmComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListVmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
