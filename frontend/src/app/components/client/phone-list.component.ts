import {Component, Input, OnInit} from '@angular/core';
import {Phone} from "../../entities/phone";
import {Person} from "../../entities/person";

@Component({
  selector: 'phone-list',
  templateUrl: './phone-list.component.html',
  styleUrls: ['./phone-list.component.css']
})
export class PhoneListComponent  {
  @Input() phoneList:Phone[];

  constructor() { }

 getPersonPhones(person: Person) {
   this.phoneList = person.phones;
 }

}
