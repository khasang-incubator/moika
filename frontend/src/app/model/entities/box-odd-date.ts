import {BaseMoikaEntity} from "./base-moika-entity";
import {SomeType} from "./some-type";
import {WorkHours} from "./work-hours";

export class BoxOddDate extends BaseMoikaEntity {
  dateX: Date;
  workHours: WorkHours;

  fromJsonObj(jsonObj :any){
   // console.log("fromJsonObj - BoxOddDates");
   // console.log(jsonObj);

    this.dateX = new Date (jsonObj.dateX);
   // console.log("WorkCalendar.dateX - "+this.dateX.toDateString());

    if (typeof jsonObj.workHours.timeOnStarts === 'object' ) {
      this.workHours = WorkHours.fromArray(jsonObj.workHours.timeOnStarts, jsonObj.workHours.timeOnEnds);
    }
    if (typeof jsonObj.workHours.timeOnStarts  === 'string' ) {
      this.workHours = WorkHours.parse(jsonObj.workHours.timeOnStarts, jsonObj.workHours.timeOnEnds);
    }

   // console.log("fromJsonObj - BoxOddDates - timeTable");
  }
}
