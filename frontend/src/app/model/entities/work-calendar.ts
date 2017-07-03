import {BaseMoikaEntity} from "./base-moika-entity";
import {SomeType} from "./some-type";
import {WorkHours} from "./work-hours";

export class WorkCalendar extends BaseMoikaEntity {
   dateX: Date;
   dateType: SomeType;
   fcltTimeTable: Array<WorkHours>;
}
