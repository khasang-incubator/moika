import {BaseMoikaEntity} from "./base-moika-entity";

export class LocalTime extends BaseMoikaEntity {
  private _hour: number = 0;
  private _minute: number = 0;
  private _second: number = 0;
  private _nano: number = 0;

  constructor(hour: number, min: number, sec: number, nano: number) {
    super();
    hour = (hour) ? hour : 0;
    min = (min) ? min : 0;
    sec = (sec) ? sec : 0;
    nano = (nano) ? nano : 0;

    this._hour = (((hour <= 24) || ( hour >= 0  )) ? Math.ceil(hour) : 0);
    this._minute = ((min <= 60) || (min >= 0 ) ? Math.ceil(min) : 0);
    this._second = ((sec <= 60) || (sec >= 0 ) ? Math.ceil(sec) : 0);
    this._nano = Math.abs(Math.ceil(nano));
  }

  /**
   * Время из даты
   * @param timeStamp  - дата будет отброшена
   * @returns {LocalTime}
   */
  public static of(timeStamp: Date): LocalTime {
    return new LocalTime(timeStamp.getHours(), timeStamp.getMinutes(), timeStamp.getSeconds(), timeStamp.getMilliseconds());
  }

  /**
   * Время из строки
   * @param time в формате "hh:mm:ss.nnn"
   * @returns {LocalTime}
   */
  public static parse(time: string): LocalTime {
    const timeStrArr: string[] = time.split(":");
    let h, m, s, n: number = 0;

    if (time) {
      if (timeStrArr.length > 0) {
        h = parseInt(timeStrArr[0]);
        if (timeStrArr.length > 1) {
          m = parseInt(timeStrArr[1]);
          if (timeStrArr.length > 2) {
            const secArr: string[] = timeStrArr[2].split(".");
            s = parseInt(secArr[0]);
            if (secArr.length > 1) {
              n = parseInt(secArr[1]);
            }
          }
        }
        return new LocalTime(h, m, s, n);
      }
    }
  }

  public toDate(): Date {
    let _d = new Date();
    _d.setTime(Date.parse(this.toString()));
    return _d;
  }

  public toString(): string {
    let _s: string;
    _s = this._hour + ":" + this._minute + ":" + this._second + "." + this._nano;
    return _s;
  }


  public toStringF(format: string): string {
    const timefrmArr: string[] = format.split(":");
    let _s: string;
    let _hh: string;
    let _mm: string;
    let _ss: string;
    let _nn: string;
    if (timefrmArr[0] == 'HH') _hh = ("00" + this._hour).slice(-2)
    else _hh = this._hour.toString();
    if (timefrmArr[1] == 'MM') _mm = ":" + ("00" + this._minute).slice(-2)
    else _mm = ":" + this._minute.toString();
    if (timefrmArr.length > 2) {
      if (timefrmArr[1] == 'ss') _ss = ":" + ("00" + this._second).slice(-2)
      else _ss = ":" + this._second.toString();
      const secArr: string[] = timefrmArr[2].split(".");
      if (secArr.length > 1) _nn = "." + this._nano.toString()
      else _nn = "";
    }
    else _ss = "";

    _s = _hh + _mm + _ss + _nn;
    return _s;
  }

  get  hour(): number {
    return this._hour;
  }

  set   hour(value: number) {
    this._hour = (((value <= 24) || ( value >= 0  )) ? Math.ceil(value) : 0);
    ;
  }

  get  minute(): number {
    return this._minute;
  }

  set   minute(value: number) {
    this._minute = ((value <= 60) || (value >= 0 ) ? Math.ceil(value) : 0);
  }

  get   second(): number {
    return this._second;
  }

  set  second(value: number) {
    this._second = ((value <= 60) || (value >= 0 ) ? Math.ceil(value) : 0);
  }

  get  nano(): number {
    return this._nano;
  }

  set  nano(value: number) {
    this._nano = Math.abs(Math.ceil(value));
    ;
  }

}
