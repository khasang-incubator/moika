import { Component, OnInit } from '@angular/core';
import { DropdownModule } from 'primeng/primeng';
import { SelectItem } from 'primeng/primeng';
import { GrowlModule } from 'primeng/primeng';
import {Message} from 'primeng/primeng';
import {MessagesModule} from 'primeng/primeng';

@Component({
  selector: 'app-reserve-order',
  templateUrl: './reserve-order.component.html',
  styleUrls: ['./reserve-order.component.css']
})
export class ReserveOrderComponent implements OnInit {
  cities: SelectItem[];
  selectedCity: any;
  msgs: Message[] = [];

  constructor() {
    this.cities = [];
    this.cities.push({ label: 'New York', value: { id: 1, name: 'New York', code: 'NY' } });
    this.cities.push({ label: 'Rome', value: { id: 2, name: 'Rome', code: 'RM' } });
    this.cities.push({ label: 'London', value: { id: 3, name: 'London', code: 'LDN' } });
    this.cities.push({ label: 'Istanbul', value: { id: 4, name: 'Istanbul', code: 'IST' } });
    this.cities.push({ label: 'Paris', value: { id: 5, name: 'Paris', code: 'PRS' } });
  }

  ngOnInit() {
  }


  showSuccess() {
    this.msgs = [];
    this.msgs.push({ severity: 'success', summary: 'Success Message', detail: 'Order submitted' });
  }
}
