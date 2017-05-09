import {Client} from './client';

let ClientList: Client[];

ClientList = [{
  idClient: 3,
  idPerson: 3,
  person: { idPerson: 3, firstName: "Семен", middleName: "Семеныч", lastName: "Горбунков", birthDate: null,
    phones: [{idPhone: 3, phoneNumber: "495-963-98-74"}],
    email: "gorbumkov@zmial.ru" },
  idStatus: 1, status: {idStatus: 1, statusCode: "ORD", statusName: "Обычный"},
  dateReg: "2017-02-17", dateLastWash: "2017-02-17"
  },
  {
    idClient: 2,
    idPerson: 2,
    person: { idPerson: 2, firstName: "Петр", middleName: "Иванович", lastName: "Сидоров", birthDate: null,
      phones: [{id: 2, number: "387-896-54-74"}],
      email: "petrov@sobako.com"},
    idStatus: 2, status: {idStatus: 2, statusCode: "VIP", statusName: "Важный"},
    dateReg: "2017-02-17", dateLastWash: "2017-02-17"
  },
  {
    idClient: 1,
    idPerson: 1,
    person: {idPerson: 1, firstName: "Марфа", middleName: "Петровна", lastName: "Горбункова", birthDate: null,
      phones: [{id: 1, number: "962-845-97-21"}],
      email: "marfa@gorbunkov.mail.ru" },
    idStatus: 1, status: {idStatus: 1, statusCode: "ORD", statusName: "Обычный"},
    dateReg: "2017-02-17",  dateLastWash: "2017-02-01"
  }];
