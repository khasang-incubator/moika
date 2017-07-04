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
      let _wA: WorkHours[] = new Array<WorkHours>(WorkHours.parse("0:00", "00:00"));
      this.fWorkWeek.push([i, 0, _wA, [_wA[0].toDate()]]);
    }
  }

  // базовое рабочее время в неделю из БД
  makeBaseWorkWeek() {
  //  let _workCalendar: WorkCalendar;
    //ищем расписание за базовую дату 1990-01-01
    let _workCalendar = this.washFacility.oddDates.find(baseDate =>
      new Date(baseDate.dateX).getTime() ===  new Date("1990-01-01").getTime() );
    let _workCalendar2: WorkCalendar = new WorkCalendar();// = JSON.parse(JSON.stringify(_workCalendar));
    console.log(JSON.stringify(_workCalendar2));
    console.log(_workCalendar2);
    _workCalendar2 = JSON.parse(JSON.stringify(_workCalendar))
    console.log(JSON.stringify(_workCalendar2));
    console.log(_workCalendar2);
    if (_workCalendar) { // если нашли
       // this.fWorkWeek.forEach(_day => console.log("Week day "+JSON.stringify(_day)));
       // console.log("_workCalendar "+JSON.stringify(_workCalendar));
     // console.log("fWorkWeek.length A ="+this.fWorkWeek.length);
      this.fWorkWeek.forEach(_day => {
        let _da = Array<any>();
        _workCalendar.fcltTimeTable.forEach(_cal => _da.push(_cal));
        _day[2] = _da;});
      this.fWorkWeek.forEach(_day => {
      //   console.log(_day[0]+" "+JSON.stringify(_day[2]));
         let _cl : WorkHours[] = _day[2];
         let _da2 = Array<any>() ;
         for (let _i = 0; _i < _cl.length; _i++ ){
           console.log("_cl = " + JSON.stringify(_cl));
           console.log(JSON.stringify(_cl[_i]));
           console.log(_cl[_i]);
            let _wh:WorkHours = WorkHours.parse(_cl[_i][0], _cl[_i][1]);
            if (_wh instanceof WorkHours) {
              console.log("_wh = " + JSON.stringify(_wh));
              console.log(_wh.timeOnStarts.toString());
              console.log(_wh.timeOnEnds.toString());
              _da2.push(_wh.toDate());
            }
            else  console.log("_wh not a HorkHours!!!");
         }
              //_da.push(_tmA.toDate())
         if (_da2) _day[3] = _da2;
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
