export interface Appointment {
  id: number;
  date: string;
  status: 'PENDING' | 'APPROVED' | 'REJECTED';
  patientName: string;
  doctorId: number;
}