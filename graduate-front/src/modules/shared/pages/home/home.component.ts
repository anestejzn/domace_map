import { Component, OnInit } from '@angular/core';
import { MapPageComponent } from '../map-page/map-page.component';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor() { }
  markers = [];
  center={lat: 44.0257707, lng: 20.7892281};

  ngOnInit(): void {}

  updateMarkers(event){
    this.markers = event;
  }

  updateCenter(event){
    this.center = event;
  }


  

}
