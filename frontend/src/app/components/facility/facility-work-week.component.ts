import {Component, Input, OnInit} from '@angular/core';
import {WashFacility} from "../../model/entities/wash-facility";
import {WorkHours} from "../../model/entities/work-hours";
import {WeekDay} from "../../model/entities/week-day";
import {WorkCalendar} from "../../model/entities/work-calendar";

@Component({
  selector: 'app-facility-work-week',
  templateUrl: './facility-work-week.component.html',
  styleUrls: ['./facility-work-week.component.css']
})
export class FacilityWorkWeekComponent implements OnInit {
  @Input() washFacility: WashFacility;
  private fWorkWeek: Array<any> = new Array();

  constructor() {
  }

  ngOnInit() {
    this.makeEmptyWorkWeek();
  }

  ngOnChanges(): void {
    this.makeEmptyWorkWeek();
    this.makeBaseWorkWeek();
    this.makeOddWeekDays();
  }

  //формируем  неделю
  makeEmptyWorkWeek() {
    if (this.fWorkWeek) {
      this.fWorkWeek.length = 0;
    }
    for (var i = 0; i < 7; i++) {
      let _wA: WorkHours[] = new Array<WorkHours>(WorkHours.parse("00:00", "00:00"));
      this.fWorkWeek.push([i, 0, _wA, [_wA[0].toDate()]]);
    }
  }

  // базовое рабочее время в неделю из БД
  makeBaseWorkWeek() {
     console.log("Enter makeBaseWorkWeek");
  //  let _workCalendar: WorkCalendar;
    //ищем расписание за базовую дату 1990-01-01
    let _workCalendar = this.washFacility.oddDates.find(baseDate =>
      new Date(baseDate.dateX).getTime() ===  new Date("1990-01-01").getTime() );
    if (_workCalendar) { // если нашли
   //   console.log("Нашли расписание по раписанию");
   //   console.log(_workCalendar);
       // this.fWorkWeek.forEach(_day => console.log("Week day "+JSON.stringify(_day)));
       // console.log("_workCalendar "+JSON.stringify(_workCalendar));
     // console.log("fWorkWeek.length A ="+this.fWorkWeek.length);
      this.fWorkWeek.forEach(_day => _day[2] = _workCalendar.timeTable);
   //   console.log("Заполнили массив WorkHours");
   //   console.log(this.fWorkWeek);
      this.fWorkWeek.forEach(_day => {
        const _wA: WorkHours[] = _day[2];
        let _da = Array<any>();
        _wA.forEach(_tmA => _da.push(_tmA.toDate()));
        _day[3] = _da;
     //       console.log(_day);
      });
      console.log("Заполнили массив WorkHours датами");
    //  this.fWorkWeek.forEach(_day => console.log("Week day "+_day[0]+" "+JSON.stringify(_day[3])));
    }
    console.log("Exit makeBaseWorkWeek");
  }

  //выходные и проиие дни в неделю
  makeOddWeekDays() {

    this.washFacility.weekDays.forEach(oddDay => {
      console.log(oddDay);
      this.fWorkWeek[oddDay.weekDay][1] = oddDay.dayType.id;
      this.fWorkWeek[oddDay.weekDay][2] = oddDay.weekTimeTable;
      //this.fWorkWeek[oddDay.weekDay][3] = oddDay.workHours.toDate();
    });
  }

}
