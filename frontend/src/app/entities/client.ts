
import {Person} from "./person";
import {BaseMoikaEntity} from "./base-moika-entity";
import {SomeStatus} from "./some-status";

export class Client extends BaseMoikaEntity {
  private _idClient: number;
  private _idPerson: number;
  private _person: Person;
  private _idStatus: number;
  private _status: SomeStatus;
  private _dateReg: Date;
  private _dateLastWash: Date;


  get idClient(): number {
    return this._idClient;
  }

  set idClient(value: number) {
    this._idClient = value;
  }

  get idStatus(): number {
    return this._idStatus;
  }

  set idStatus(value: number) {
    this._idStatus = value;
  }

  get status(): SomeStatus {
    return this._status;
  }

  set status(value: SomeStatus) {
    this._status = value;
  }

  get dateReg(): Date {
    return this._dateReg;
  }

  set dateReg(value: Date) {
    this._dateReg = value;
  }

  get dateLastWash(): Date {
    return this._dateLastWash;
  }

  set dateLastWash(value: Date) {
    this._dateLastWash = value;
  }

  get idPerson(): number {
    return this._idPerson;
  }

  set idPerson(idPerson: number) {
    this._idPerson = idPerson;
  }

  get person(): Person {
    return this._person;
  }

  set person(person: Person) {
    this._person = person;
  }
}
