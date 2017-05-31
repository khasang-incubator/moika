import {SelectItem} from 'primeng/primeng';

export class StatusRefList {

  statusRefs:  SelectItem[];

  constructor() {
    this.statusRefs = [];
    this.statusRefs.push({label:'Выбирите справочник статусов', value:null});
    this.statusRefs.push({label:'Статусы клиентов', value:{id:1, name: 'clientStatus', url: 'clientStatus/list'}});
    this.statusRefs.push({label:'Статусы услуг', value:{id:2, name: 'serviceStatus', url: 'serviceStatus/list'}});
    this.statusRefs.push({label:'Статусы пользователей', value:{id:3, name: 'userStatus', url: 'userStatus/list'}});
    this.statusRefs.push({label:'Статусы боксов', value:{id:4, name: 'boxStatus', url: 'boxStatus/list'}});
  }

}
