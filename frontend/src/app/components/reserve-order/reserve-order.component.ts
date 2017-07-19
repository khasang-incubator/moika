import { Component, OnInit } from '@angular/core';
import { DropdownModule } from 'primeng/primeng';
import { SelectItem } from 'primeng/primeng';
import { GrowlModule } from 'primeng/primeng';
import {Message} from 'primeng/primeng';
import {MessagesModule} from 'primeng/primeng';
import {CalendarModule} from 'primeng/primeng';
import {FreeboxDays} from 'app/model/entities/freebox-days';
import {FreeboxHours} from 'app/model/entities/freebox-hour';
import {FreeboxDaysService}from 'app/model/services/freebox-days-service';
import {FreeboxHoursService} from "../../model/services/freebox-hours-service";
import { Response} from '@angular/http';
import {DataTableModule,SharedModule} from 'primeng/primeng';


@Component({
  selector: 'app-reserve-order',
  templateUrl: './reserve-order.component.html',
  styleUrls: ['./reserve-order.component.css']
})
export class ReserveOrderComponent implements OnInit {
  cities: SelectItem[];
  selectedCity: any;
  carwashs: SelectItem[];
  selectedCarwash: any;
  msgs: Message[] = [];
  date1: Date = new Date();
  date2: Date = new Date();
  freeBoxDay:FreeboxDays[];
  freeTime: FreeboxHours[];


  constructor(private freeboxDaysService:FreeboxDaysService,
              private freeboxHoursService:FreeboxHoursService) {
    this.cities = [];
    this.cities.push({ label: 'Длинный New York', value: { id: 1, name: 'New York', code: 'NY' } });
    this.cities.push({ label: 'Rome', value: { id: 2, name: 'Rome', code: 'RM' } });
    this.cities.push({ label: 'London', value: { id: 3, name: 'London', code: 'LDN' } });
    this.cities.push({ label: 'Istanbul', value: { id: 4, name: 'Istanbul', code: 'IST' } });
    this.cities.push({ label: 'Paris', value: { id: 5, name: 'Paris', code: 'PRS' } });
    this.carwashs = [];
    this.carwashs.push({ label: 'Мойка 1', value: { id: 1, name: 'Мойка 1', code: '1' } });
    this.carwashs.push({ label: 'Длинная предлинная Мойка 2 и еще с длинным прицепом',
    value: { id: 2, name: 'Длинная предлинная Мойка 2 и еще с длинным прицепом', code: '3' } });
    this.carwashs.push({ label: 'Мойка 3', value: { id: 3, name: 'Мойка 3', code: '3' } });

  }

  ngOnInit() {
  }
  onChangeCity(event){
    console.log(event.value);
    console.log(event.originalEvent);
    this.msgs = [];
    this.msgs.push({ severity: 'success', summary: 'Выбран город', detail: this.selectedCity.name });
  }
onChangeCarWash(event){
    console.log(event.value);
    console.log(event.originalEvent);
    this.msgs = [];
    this.msgs.push({ severity: 'success', summary: 'Выбранa мойка', detail: this.selectedCarwash.name });
  }
  findFreeBoxDay(event){
    this.freeboxDaysService.getData().then(fb => this.freeBoxDay = fb);
    }
  onDaySelected(event){
    console.log(event.data);
    this.freeTime = this.freeboxHoursService.getFreeTime();
  }
  onTimeSelected(event){
    console.log(event.data);
    this.msgs = [];
    this.msgs.push({ severity: 'success', summary: 'Предложение сделать заказ на ', detail: event.data.time });
  }
}
