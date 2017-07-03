import {Component, Input, OnInit} from '@angular/core';
import {WashFacility} from "../../model/entities/wash-facility";

@Component({
  selector: 'app-facility-calendar',
  templateUrl: './facility-calendar.component.html',
  styleUrls: ['./facility-calendar.component.css']
})
export class FacilityCalendarComponent implements OnInit {

  @Input() washFacility: WashFacility;
  private today: Date = new Date();
  private selectedDate: Date;
  private month: number = this.today.getMonth();
  private year: number = this.today.getFullYear();
  private prevMonth: number ;
  private prevYear: number ;
  private nextMonth: number ;
  private nextYear: number ;
  private minDate: Date= new Date();
  private maxDate : Date= new Date();
  private oddDates: Array<Date>= new Array();
  private oddDays: Array<number> = new Array(7);
  private ru:any;
  constructor() { }

  ngOnInit() {

    this.ru = {
      firstDayOfWeek: 1,
      dayNames: ["воскресенье", "понедельник","вторник","среда","четверг","пятница","суббота" ],
      dayNamesShort: [ "вс","пн","вт","ср","чт","пт","сб" ],
      dayNamesMin: [ "В","П","В","С","Ч","Пт","Сб" ],
      monthNames: [ "январь","февраль","март","апрель","май","июнь","июль","август","сентябрь","октябрь","ноябрь","декабрь" ],
      monthNamesShort: [ "янв","фев","мар","апр","май","июн","июл","авг","сен","окт","нов","дек" ]
    }

    this.selectedDate = this.today;
    this.prevMonth= (this.month === 0) ? 11 : this.month -1;
    this.prevYear = (this.prevMonth === 11) ? this.year - 1 : this.year;
    this.nextMonth = (this.month === 11) ? 0 :this. month + 1;
    this.nextYear =  this.year + 1 ;
    this.minDate.setMonth(this.prevMonth);
    this.minDate.setFullYear(this.prevYear);
    this.maxDate.setMonth(this.nextMonth);
    this.maxDate.setFullYear(this.nextYear);
 //   if (this.washFacility){
  //    this.washFacility.fcltOddOffDays.forEach(oddDate=>this.oddDates.push(oddDate.dateX));
 //     this.washFacility.fcltWeekOffDays.forEach(oddDay=>this.oddDays.push(oddDay.weekDay));
  //  }
 //   let invalidDate = new Date();
  //  invalidDate.setDate(this.today.getDate() - 1);
  //  this.oddDates = [this.today,invalidDate];
  }

  ngOnChanges(): void {
    if (this.washFacility){
      this.selectedDate = this.today;
      console.log(JSON.stringify(this.washFacility.oddDates));
      console.log(JSON.stringify(this.washFacility.weekDays));
      this.oddDates.length=0;
      this.oddDays.length=0;
      this.washFacility.oddDates.forEach(oddDate=>{
        if (oddDate.dateType.typeCode != "WORK") {
          this.oddDates.push(new Date(oddDate.dateX));
        }
      });
      this.washFacility.weekDays.forEach(oddDay=>this.oddDays.push(oddDay.weekDay.valueOf()));
      console.log(JSON.stringify(this.oddDates));
      console.log(JSON.stringify(this.oddDays));
    }
  }
}
