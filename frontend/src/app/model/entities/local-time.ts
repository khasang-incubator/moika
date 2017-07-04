import {BaseMoikaEntity} from "./base-moika-entity";

interface ILocalTime{
  hour: number;
  minute: number;
  second: number;
  nano: number;
}

export class LocalTime extends BaseMoikaEntity implements ILocalTime{
  private _hour: number = 0;
  private _minute: number = 0;
  private _second: number = 0;
  private _nano: number = 0;


  constructor( ) {
    super();
  }

  /**
   * Время из чисел
   * @param
   * @returns {LocalTime}
   */

  public static from(hour: number, min: number, sec: number, nano: number): LocalTime {
    let retTime = new LocalTime();
    hour = (hour) ? hour : 0;
    min = (min) ? min : 0;
    sec = (sec) ? sec : 0;
    nano = (nano) ? nano : 0;

    retTime.hour = (((hour < 24) || ( hour >= 0  )) ? Math.ceil(hour) : 0);
    retTime.minute = ((min < 60) || (min >= 0 ) ? Math.ceil(min) : 0);
    retTime.second = ((sec < 60) || (sec >= 0 ) ? Math.ceil(sec) : 0);
    retTime.nano = Math.abs(Math.ceil(nano));
    return retTime;
  }

  /**
   * Время из даты
   * @param timeStamp  - дата будет отброшена
   * @returns {LocalTime}
   */
  public static of(timeStamp: Date): LocalTime {
    let retTime = new LocalTime();
    retTime.hour = timeStamp.getHours();
    retTime.minute = timeStamp.getMinutes();
    retTime.second = timeStamp.getSeconds();
    retTime.nano = timeStamp.getMilliseconds();
    return retTime;
  }

    /**
   * Время из строки
   * @param time в формате "hh:mm:ss.nnn"
   * @returns {LocalTime}
   */
  public static parse(time: string): LocalTime {
    const timeStrArr: string[] = time.split(":");
    let retTime = new LocalTime();
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
        retTime.hour = h;
        retTime.minute = m;
        retTime.second = s;
        retTime.nano = n;
        return retTime;
      }
    }
  }

  public toDate(): Date {
    let _d = new Date();
    _d.setHours(this._hour);
    _d.setMinutes(this._minute);
    _d.setSeconds(this._second);
    _d.setMilliseconds(this._second);
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
