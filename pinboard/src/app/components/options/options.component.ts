import { Component, OnInit } from '@angular/core';
import { Preference } from 'src/app/models/preference';
import { PreferencesService } from 'src/app/services/preferences/preferences.service';

@Component({
  selector: 'app-options',
  templateUrl: './options.component.html',
  styleUrls: ['./options.component.css']
})
export class OptionsComponent implements OnInit {

  private options: Preference[];

  constructor(private preferenceService: PreferencesService) { }

  ngOnInit() {
    this.getPreferences();
  }

  private getPreferences(): void {
    this.preferenceService.getPreferences().subscribe(options => {
      this.options = options;
    })
  }

  private cancel(): void {
    this.getPreferences();
  }

  private saveChanges(): void {
    this.preferenceService.saveChanges(this.options).subscribe(() => {
      this.getPreferences();
    });
  }

}
