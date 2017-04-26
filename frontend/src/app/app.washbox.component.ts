import {Component, OnInit} from '@angular/core';
import {WashBoxService} from './app.washbox.service';
import {WashBox} from './washbox';

@Component({
  selector: 'washbox-list',
  templateUrl: './app.washbox.component.html',
  styleUrls: ['../css/bootstrap.min.css']
})
export class WashBoxComponent implements OnInit {
  washBoxList: Array<WashBox> = [];
  selectedBox: WashBox;
  washBoxService: WashBoxService;

  public WashBoxComponent(washBoxService: WashBoxService) {
    this.washBoxService = washBoxService;
  }

  getAll(): void {
    this.washBoxService.getAll().then(washBoxList => this.washBoxList = washBoxList);
  }

  ngOnInit(): void {
    this.getAll();
  }

  onSelect(washBox: WashBox): void {
    this.selectedBox = washBox;
  }

}
