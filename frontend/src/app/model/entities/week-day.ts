import {BaseMoikaEntity} from "./base-moika-entity";
import {SomeType} from "./some-type";
import {Calendar} from "../collections/calendar";
import DayOfWeek = Calendar.DayOfWeek;
import {WorkHours} from "./work-hours";

export class WeekDay extends BaseMoikaEntity {
    weekDay: DayOfWeek;
    dayType: SomeType;
    workHours:  Array<WorkHours>;
}
