import { Component, Input, OnDestroy, OnInit, SimpleChanges, ViewChild } from "@angular/core";
import { MapInfoWindow, MapMarker } from "@angular/google-maps";

@Component({
  selector: 'map-page',
  templateUrl: './map-page.component.html',
  styleUrls: ['./map-page.component.css'],
})
export class MapPageComponent implements OnInit, OnDestroy {
  @Input() markers: any[];
  @Input() center: {lat: number, lng: number};
  @ViewChild(MapInfoWindow, { static: false }) infoWindow: MapInfoWindow
  infoContent = {name: '', description: '', household: '', householdId: 0, average: 0, price: 0, colForPrice: ''};
  zoom = 7.2;
  options = {
    mapTypeId: 'hybrid',
    zoomControl: true,
    scrollwheel: true,
    disableDoubleClickZoom: true,
    fullscreenControl: false,
    mapTypeControl: false,
    // maxZoom: 15,
    // minZoom: 10,
    center: {lat: 44.0257707, lng: 20.7892281},
    zoom: 10
  };

  constructor(){
  }

  ngOnDestroy(): void {
  
  }
  ngOnInit(): void {}
  openInfo(marker: MapMarker, content) {
   
    this.infoContent = content;
    this.infoWindow.open(marker);
  }

  ngOnChanges(simpleChanges : SimpleChanges){
    if(this.markers.length > 0){
      this.zoom = 10;
    }
    else{
      this.zoom = 7.2;
    }
    if(simpleChanges['center']) {
      this.options.center = simpleChanges['center'].currentValue;
    }
    
  }


}
