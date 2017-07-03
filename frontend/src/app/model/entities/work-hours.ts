import {BaseMoikaEntity} from "./base-moika-entity";
import {LocalTime} from "./local-time";

export class WorkHours extends BaseMoikaEntity {
  timeOnStarts: LocalTime;
  timeOnEnds: LocalTime;

  constructor(ts: LocalTime, te: LocalTime) {
    super();
    this.timeOnStarts = ts;
    this.timeOnEnds = te;
  }

  public static fromArray(ts: number[], te: number[]) {
    let _tsA: number[] = [0, 0, 0, 0];
    let _teA: number[] = [0, 0, 0, 0];
    let _al: number;
    _al = (ts.length >= 4) ? 4 : ts.length;
    for (var i = 0; i < _al; i++) _tsA[i] = ts[i];

    _al = (te.length >= 4) ? 4 : te.length;
    for (var i = 0; i < _al; i++) _teA[i] = te[i];

    let _ts = new LocalTime(ts[0], ts[1], ts[2], ts[3]);
    let _te = new LocalTime(te[0], te[1], te[2], te[3]);
    return new WorkHours(_ts, _te);
  }

  public static parse(ts: string, te: string): WorkHours {
    let _ts = LocalTime.parse(ts);
    let _te = LocalTime.parse(te);
    return new WorkHours(_ts, _te);
  }

  public toDate(): Array<Date>{
    let _retA = new Array<Date>();
    _retA.push(this.timeOnStarts.toDate());
    _retA.push(this.timeOnEnds.toDate())
    return _retA;
  }
}
