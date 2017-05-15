import {Component, Input, OnInit} from '@angular/core';
import {WashBoxService} from '../../model/services/wash-box.service';
import {WashBox} from '../../model/entities/wash-box';

@Component({
  selector: 'washbox-list',
  templateUrl: './wash-box.component.html',
  styleUrls: ['./wash-box.component.css']
})
export class WashBoxComponent {
  @Input() washBoxList: Array<WashBox> = [];
  selectedBox: WashBox;
  washBoxService: WashBoxService;

  constructor(washBoxService: WashBoxService) {
    this.washBoxService = washBoxService;
  }

  getAll(): void {
    this.washBoxService.getAll().then(washBoxList => this.washBoxList = washBoxList).catch(this.handleError);
  }

  onSelect(washBox: WashBox): void {
    this.selectedBox = washBox;
  }

  private handleError(error: any): Promise<any> {
    console.error('Не могу получить список боксов. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
