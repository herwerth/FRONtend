import { AccountDetails } from './accountDetails.model';

export interface AuthResponse {
  accessToken: string;
  refreshToken: string;
  accountDetails: AccountDetails;
}
