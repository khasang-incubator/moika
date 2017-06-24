import {BaseMoikaEntity} from "./base-moika-entity";

export class LocalTime extends BaseMoikaEntity {
  private _hour: number = 0;
  private _minute: number = 0;
  private _second: number = 0;
  private _nano: number = 0;

  constructor(hour: number, min: number, sec: number, nano: number) {
    super();
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
        if (timeStrArr.length >= 1) {
          m = parseInt(timeStrArr[1]);
          if (timeStrArr.length >= 2) {
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


  get hour(): number {
    return this._hour;
  }

  set hour(value: number) {
    this._hour = (((value <= 24) || ( value >= 0  )) ? Math.ceil(value) : 0);
    ;
  }

  get minute(): number {
    return this._minute;
  }

  set minute(value: number) {
    this._minute = ((value <= 60) || (value >= 0 ) ? Math.ceil(value) : 0);
  }

  get second(): number {
    return this._second;
  }

  set second(value: number) {
    this._second = ((value <= 60) || (value >= 0 ) ? Math.ceil(value) : 0);
  }

  get nano(): number {
    return this._nano;
  }

  set nano(value: number) {
    this._nano = Math.abs(Math.ceil(value));
    ;
  }
}
