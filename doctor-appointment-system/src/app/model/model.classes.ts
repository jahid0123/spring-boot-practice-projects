export interface AppointmentRequest {
  patientId: number;
  doctorId: number;
    date: string;
  time: string;
}

export interface DoctorRegisterRequestDto {
  name: string;
  email: string;
  password: string;
  specialization: string;
  qualification: string;
  experience: number;
  hospitalName: string;
  phone: string;
}

export interface DoctorResponseDto {
  id: number;
  name: string;
  specialization: string;
  qualification: string;
  experience: number;
  hospitalName: string;
  phone: string;
  image: string; // image URL or base64
}

export interface DoctorListDto {
  id: number;
  name: string;
  specialization: string;
  qualification: string;
  experience: number;
  hospitalName: string;
  phone: string;
  image: string
}

export interface GetAppointmentsPatient {
  doctorId: number;
  patientId: number;
  appointmentId: number;
  doctorName: string;
  qualification: string;
  patientName: string;
  patientDob: string; // ISO string or convert to string from LocalDate
  patientGender: string;
  appointmentDate: string;
  appointmentTime: string;
  appointmentStatus: string; // Or use an enum if available
}

export interface Medicine {
  name: string;
  dosage: string;
  frequency: string;
  duration: string;
}

export interface PrescriptionRequest {
  symptoms: string;
  diagnosis: string;
  medicines: Medicine[];
  appointmentId: number;
  doctorId: number;
  patientId: number;
}

export interface PrescriptionDto {
  symptoms: string;
  diagnosis: string;
  medicines: Medicine[];
  appointmentId: number;
  doctorId: number;
  patientId: number;
}

