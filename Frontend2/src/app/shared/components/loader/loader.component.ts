import { Component, OnInit, Input } from '@angular/core';

/** Creates the loadng animation on buttons */
@Component( {
    selector: 'app-loader',
    templateUrl: './loader.component.html',
    styleUrls: [ './loader.component.scss' ]
} )
export class LoaderComponent implements OnInit {
    @Input() isLoading: boolean;

    constructor() {}

    ngOnInit() { }

}

