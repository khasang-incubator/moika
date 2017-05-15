import {SelectItem} from 'primeng/primeng';

export class TypeRefList {

  typeRefs:  SelectItem[];

  constructor() {
    this.typeRefs = [];
    this.typeRefs.push({label:'Выбирите справочник типов', value:null});
    this.typeRefs.push({label:'Типы клиентов', value:{id:1, name: 'clientType', url: 'clientType/list'}});
    this.typeRefs.push({label:'Типы услуг', value:{id:2, name: 'serviceType', url: 'serviceType/list'}});
    this.typeRefs.push({label:'Типы машин', value:{id:3, name: 'carType', url: 'carType/list'}});
    this.typeRefs.push({label:'Типы боксов', value:{id:4, name: 'boxType', url: 'boxType/list'}});
    this.typeRefs.push({label:'Типы загрязнений', value:{id:5, name: 'dirtType', url: 'dirtType/list'}});
  }

}
