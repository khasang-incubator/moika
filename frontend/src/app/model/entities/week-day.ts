import {BaseMoikaEntity} from "./base-moika-entity";
import {SomeType} from "./some-type";
import {Calendar} from "../collections/calendar";
import DayOfWeek = Calendar.DayOfWeek;
import {WorkHours} from "./work-hours";

export class WeekDay extends BaseMoikaEntity {
    weekDay: DayOfWeek;
    dayType: SomeType;
    weekTimeTable:  Array<WorkHours>;

    fromJsonObj(jsonObj :any) {
    //  console.log("fromJsonObj - WeekDay  - Enter");
    //  console.log(jsonObj);
      this.weekDay = jsonObj.weekDay;
   //   console.log(jsonObj.dayType);
      this.dayType = jsonObj.dayType;
   //   console.log(this.dayType);
     // Object.assign(this.weekDay,  jsonObj.weekDay);
     // Object.assign(this.dayType,  jsonObj.dayType);
      this.weekTimeTable = new Array<WorkHours>();
      jsonObj.weekTimeTable.forEach(rec => {
        let _h : WorkHours;
        if (typeof rec.workHours.timeOnStarts === 'object' ) {
          _h = WorkHours.fromArray(rec.workHours.timeOnStarts, rec.workHours.timeOnEnds);
        }
        if (typeof rec.workHours.timeOnStarts  === 'string' ) {
          _h = WorkHours.parse(rec.workHours.timeOnStarts, rec.workHours.timeOnEnds);
        }
        this.weekTimeTable.push(_h);
      });

   //   console.log("fromJsonObj - WeekDay - Exit");
    }
}
