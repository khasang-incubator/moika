import {BaseMoikaEntity} from "./base-moika-entity";
import {SomeType} from "./some-type";
import {Calendar} from "../collections/calendar";
import DayOfWeek = Calendar.DayOfWeek;
import {WorkHours} from "./work-hours";

export class BoxWeekDay extends BaseMoikaEntity {
  weekDay: DayOfWeek;
  workHours:  WorkHours;

  fromJsonObj(jsonObj :any) {
  //  console.log("fromJsonObj - BoxWeekDay");
  // console.log(jsonObj);
    this.weekDay =  jsonObj.weekDay;

    if (typeof jsonObj.workHours.timeOnStarts === 'object' ) {
      this.workHours = WorkHours.fromArray(jsonObj.workHours.timeOnStarts, jsonObj.workHours.timeOnEnds);
    }
    if (typeof jsonObj.workHours.timeOnStarts  === 'string' ) {
      this.workHours = WorkHours.parse(jsonObj.workHours.timeOnStarts, jsonObj.workHours.timeOnEnds);
    }
  //  console.log("BoxWeekDay - WeekDay - workHours");
  }
}

