import {BaseMoikaEntity} from "./base-moika-entity";
import {SomeStatus} from "./some-status";
import {SomeType} from "./some-type";
import {WeekDay} from "./week-day";
import {WorkCalendar} from "./work-calendar";
/*
 Wash Box FrontEnd REST entity
 */
export class WashBox extends BaseMoikaEntity {
   id: number;
   idFacility: number;
   boxName: string;
   description: string;
   idType: number;
   boxTypeEntity: SomeType;
   idStatus: number;
   boxStatusEntity: SomeStatus;
   oddOffDays:  Array<WorkCalendar>;
   weekOffDays:  Array<WeekDay>;
}
