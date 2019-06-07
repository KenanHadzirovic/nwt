import { PreferenceType } from './preferenceType';

export class Preference {
    preferenceId: number;
    value: string;
    userId: number;
    preferenceType: PreferenceType;
    boolValue: boolean;
}