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
      let _wA: WorkHours[] = new Array<WorkHours>(WorkHours.parse("0:00", "00:00"));0
      this.fWorkWeek.push([i, 0, _wA, [_wA[0].toDate()]]);
    }
  }

  // базовое рабочее время в неделю из БД
  makeBaseWorkWeek() {
    let _workCalendar: WorkCalendar;
    //ищем расписание за базовую дату 1990-01-01
    _workCalendar = this.washFacility.oddDates.find(baseDate =>
      new Date(baseDate.dateX).getTime() ===  new Date("1990-01-01").getTime() );
    if (_workCalendar) { // если нашли
       // this.fWorkWeek.forEach(_day => console.log("Week day "+JSON.stringify(_day)));
       // console.log("_workCalendar "+JSON.stringify(_workCalendar));
     // console.log("fWorkWeek.length A ="+this.fWorkWeek.length);
      this.fWorkWeek.forEach(_day => {
        let _da = Array<any>();
        _workCalendar.fcltTimeTable.forEach(_cl => _da.push(_cl));
        _day[2] = _da;});
      this.fWorkWeek.forEach(_day => {
      //   console.log(_day[0]+" "+JSON.stringify(_day[2]));
         let _cl : WorkHours[] = _day[2];
         let _da = Array<any>() ;
         for (let _i = 0; _i < _cl.length; _i++ ){
            let _wh: WorkHours = _cl[_i];
           console.log("_wh = "+JSON.stringify(_wh));
           _da.push( _wh.toDate());
         }
              //_da.push(_tmA.toDate())
         if (_da) _day[3] = _da;
      });
      this.fWorkWeek.forEach(_day => console.log("Week day "+_day[0]+" "+JSON.stringify(_day[3])));
    }
  }

  //выходные и проиие дни в неделю
  makeOddWeekDays() {
    this.washFacility.weekDays.forEach(oddDay => {
      this.fWorkWeek[oddDay.weekDay][1] = oddDay.dayType.id;
      this.fWorkWeek[oddDay.weekDay][2] = oddDay.workHours;
      //this.fWorkWeek[oddDay.weekDay][3] = oddDay.workHours.toDate();
    });
  }

}
