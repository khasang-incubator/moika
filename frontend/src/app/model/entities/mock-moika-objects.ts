import {MoikaObject} from "./moika-object";
import {Injectable} from "@angular/core";

@Injectable()
export class MockMoikaObjects {

  private objectList: MoikaObject[];

  constructor() {
    this.objectList = [];
    // типы
    this.objectList.push({
      code: "clientType",
      name: 'Типы клиентов',
      descr: 'Типы клиентов автомоек',
      type: 1,
      url: 'api/client/type'
    });
    this.objectList.push({
      code: "serviceType",
      name: 'Типы услуг',
      descr: 'Типы услуг автомоек',
      type: 1,
      url: 'api/service/type'
    });
    this.objectList.push({
      code: "carType",
      name: 'Типы машин',
      descr: 'Типы машин клиентов',
      type: 1,
      url: 'api/car/type'
    });
    this.objectList.push({
      code: "boxType",
      name: 'Типы боксов',
      descr: 'Типы моечных боксов',
      type: 1,
      url: 'api/washBox/type'
    });
    this.objectList.push({
      code: "dirtType",
      name: 'Типы загрязнений',
      descr: 'Типы загрязнений салона',
      type: 1,
      url: 'api/service/dirt/type'
    });

// Статусы
    this.objectList.push({
      code: "clientStatus",
      name: 'Статусы клиентов',
      descr: 'Статусы клиентов автомоек',
      type: 2,
      url: 'api/client/status'
    });
    this.objectList.push({
      code: "serviceStatus",
      name: 'Статусы услуг',
      descr: 'Статусы услуг автомоек',
      type: 2,
      url: 'api/service/status'
    });
    this.objectList.push({
      code: "carStatus",
      name: 'Статусы машин',
      descr: 'Статусы машин клиентов',
      type: 2,
      url: 'api/car/status'
    });
    this.objectList.push({
      code: "boxStatus",
      name: 'Статусы боксов',
      descr: 'Статусы моечных боксов',
      type: 2,
      url: 'api/washBox/status'
    });
  }

  getObjects(type: number|null|undefined): Promise<MoikaObject[]> {
    if (type) {
      return Promise.resolve(this.objectList.filter(value => type == value.type));
    }
    return Promise.resolve(this.objectList);
  }

  getObject(code: string): MoikaObject {
    if (code) {
      return this.objectList.find(value => code == value.code);
    }
    return undefined;
  }

}
