import {BaseMoikaEntity} from "./base-moika-entity";
import {LocalTime} from "./local-time";

export class WorkHours extends BaseMoikaEntity {
  timeOnStarts: LocalTime;
  timeOnEnds: LocalTime;
}
