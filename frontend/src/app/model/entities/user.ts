import {Person} from "./person";
import {BaseMoikaEntity} from "./base-moika-entity";

export class User extends BaseMoikaEntity {
  private _idUser: number;
  private _login: string;
  private _password: string;
  private _timeCreate: Date;
  private _timeEdit: Date;
  private _idPerson: number;
  private _person: Person;


  get idUser(): number {
    return this._idUser;
  }

  get login(): string {
    return this._login;
  }

  set login(value: string) {
    this._login = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }

  get timeCreate(): Date {
    return this._timeCreate;
  }

  set timeCreate(value: Date) {
    this._timeCreate = value;
  }

  get time_Edit(): Date {
    return this._timeEdit;
  }

  set time_Edit(value: Date) {
    this._timeEdit = value;
  }

  get id_Person(): number {
    return this._idPerson;
  }

  set id_Person(value: number) {
    this._idPerson = value;
  }

  get person(): Person {
    return this._person;
  }

  set person(value: Person) {
    this._person = value;
  }
}
