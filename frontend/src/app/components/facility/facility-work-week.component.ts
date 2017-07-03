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
      let _wA = new Array<WorkHours>(WorkHours.parse("00:00", "00:00"));
      this.fWorkWeek.push(i, 0, _wA, [_wA[0].toDate()]);
    }
  }

  // базовое рабочее время в неделю
  makeBaseWorkWeek() {
    let _workCalendar: WorkCalendar;
    _workCalendar = this.washFacility.fcltOddDays.find(baseDate =>
      new Date(baseDate.dateX).getTime() ===  new Date("1990-01-01").getTime() );
    console.log("_workCalendar 1"+JSON.stringify(_workCalendar));
    if (_workCalendar) {
      this.fWorkWeek.forEach(_day => _day[2] = _workCalendar.fcltTimeTable);
      console.log("_workCalendar 2"+JSON.stringify(_workCalendar));
      this.fWorkWeek.forEach(_day => {
        let _da = Array<any>();
        _day[2].forEach(_tmA => _da.push(_tmA.toDate()));
        _day[3] = _da;
      });
    }
  }

  //выходные и проиие дни в неделю
  makeOddWeekDays() {
    this.washFacility.fcltWeekDays.forEach(oddDay => {
      this.fWorkWeek[oddDay.weekDay][2] = oddDay.workHours;
      this.fWorkWeek[oddDay.weekDay][1] = oddDay.dayType.id;
    });
  }

}
