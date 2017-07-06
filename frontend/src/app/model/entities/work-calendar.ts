import {BaseMoikaEntity} from "./base-moika-entity";
import {SomeType} from "./some-type";
import {WorkHours} from "./work-hours";

export class WorkCalendar extends BaseMoikaEntity {
   dateX: Date;
   dateType: SomeType;
   timeTable: Array<WorkHours>;

   fromJsonObj(jsonObj :any){
  //   console.log("fromJsonObj - WorkCalendar");
 //    console.log(jsonObj);

     this.dateX = new Date (jsonObj.dateX);
  //   console.log("WorkCalendar.dateX - "+this.dateX.toDateString());
     this.dateType = jsonObj.dateType;
  //   console.log("WorkCalendar.dateType ");
     this.timeTable = new Array<WorkHours>();
     jsonObj.timeTable.forEach(rec => {
       let _h : WorkHours;
       if (typeof rec.workHours.timeOnStarts === 'object' ) {
         _h = WorkHours.fromArray(rec.workHours.timeOnStarts, rec.workHours.timeOnEnds);
       }
       if (typeof rec.workHours.timeOnStarts  === 'string' ) {
         _h = WorkHours.parse(rec.workHours.timeOnStarts, rec.workHours.timeOnEnds);
       }
    //   console.log(_h);
       this.timeTable.push(_h);
     });
  //  console.log("fromJsonObj - WorkCalendar - timeTable" + this.dateX.toDateString());
 //   console.log(this.timeTable);
  }
}
