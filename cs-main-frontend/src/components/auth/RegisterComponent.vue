<template>
  <form @submit.prevent="submitRegister()"
        class="login">
    <label for="name">Name</label>
    <input type="text" v-model="authData.name" required>
    <small id="nameErrors"
           class="error"
           v-if="errors.name.length>0">
      {{ errors.name.reduce((prev, curr) => prev + ", " + curr) }} </small>
    <label for="surname">Surname</label>
    <input type="text" v-model="authData.surname" required>
    <small id="surnameErrors"
           class="error"
           v-if="errors.surname.length>0">
      {{ errors.surname.reduce((prev, curr) => prev + ", " + curr) }} </small>

    <label for="username">Email</label>
    <input @focusout="validateLoginField()" type="text" v-model="authData.email" required>
    <small id="emailErrors"
           class="error"
           v-if="errors.email.length>0">
      {{ errors.email.reduce((prev, curr) => prev + ", " + curr) }}</small>

    <label for="phoneNumber">Phone number</label>
    <input type="text" v-model="authData.phoneNumber" v-mask="'+380(##)### ####'" required>
    <small id="phoneNumberErrors"
           class="error"
           v-if="errors.phoneNumber.length>0">
      {{ errors.phoneNumber.reduce((prev, curr) => prev + ", " + curr) }} </small>

    <label for="username">Password</label>
    <input @focusout="validatePasswordField()"
           type="text"
           v-model="authData.password"
           min="8"
           max="255"
           required>
    <small id="passwordErrors"
           class="error"
           v-if="errors.password.length>0">
      {{ errors.password.reduce((prev, curr) => prev + ", " + curr) }} </small>

    <label for="dateOfBirthday">Date of birthday</label>
    <input id="dateInput"
           type="date"
           v-model="authData.dateOfBirthday"
           :max="new Date().toISOString().split('T')[0]"
           required>
    <small id="dateOfBirthdayErrors"
           class="error"
           v-if="errors.dateOfBirthday.length>0">
      {{ errors.dateOfBirthday.reduce((prev, curr) => prev + ", " + curr) }} </small>
    <label for="gender">Gender</label>
    <label for="male">
      <input type="radio" id="male" value="male" v-model="authData.gender" checked required>Male</label>
    <label for="female">
      <input type="radio" id="female" value="female" v-model="authData.gender">Female</label>
    <button type="submit">Register</button>
    <small id="apiErrors"
           class="error"
           v-if="errors.api.length>0">{{ errors.api.reduce((prev, curr) => prev + ", " + curr) }}</small>
  </form>

</template>

<script>
import axios from 'axios';
import {mask} from 'vue-the-mask'


export default {
  name: "RegisterComponent",
  directives: {mask},
  data() {
    return {
      errors: {
        email: [],
        password: [],
        dateOfBirthday: [],
        gender: [],
        phoneNumber: [],
        name: [],
        surname: [],
        api: []
      },
      authData: {
        email: '',
        password: '',
        dateOfBirthday: '',
        gender: '',
        phoneNumber: '+380',
        name: '',
        surname: ''
      }
    }
  },
  methods: {
    validateLoginField() {
      let emailRegex = '^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$';
      for (let i = 0; i < this.errors.email.length; i++) {
        this.errors.email.pop();
      }
      if (!this.authData.email.match(emailRegex)) {
        this.errors.email.push('Please enter a valid email address');
      }
    },
    validatePasswordField() {
      for (let i = 0; i < this.errors.password.length; i++) {
        this.errors.password.pop();
      }
      if (this.authData.password.length < 4 || this.authData.password.length > 255) {
        this.errors.password.push('Put a password between 8 and 255 characters');
      }
    },
    async submitRegister() {
      for (let i = 0; i < this.errors.api.length; i++) {
        this.errors.api.pop();
      }

      for (const x in this.errors) {
        for (let i = 0; i < this.errors[x].length; i++) {
          console.log('errors')
          return;
        }
      }
      let data = JSON.stringify(this.authData);

      try {
        const apiResponse = await axios.post('/api/v1/auth/register', data, {
          headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE"
          }
        });

        if (apiResponse.status === 200) {
          localStorage.setItem('token', apiResponse.data.token);
          this.$router.push('/');
        }
      } catch (e) {
        this.errors.api.push(e.response.data);
      }


    }
  }
}
</script>

<style scoped>

button {
  margin-top: 50px;
  width: 100%;
  background-color: #ffffff;
  color: #080710;
  padding: 15px 0;
  font-size: 18px;
  font-weight: 600;
  border-radius: 5px;
  cursor: pointer;
}

form {
  height: 520px;
  width: 400px;
  background-color: burlywood;
  position: absolute;
  transform: translate(-50%, -50%);
  top: 50%;
  left: 50%;
  border-radius: 10px;
  backdrop-filter: blur(10px);
  border: 2px solid rgba(255, 255, 255, 0.1);
  box-shadow: 0 0 40px rgba(8, 7, 16, 0.6);
  padding: 50px 35px;
}

form * {
  font-family: 'Poppins', sans-serif;
  color: #ffffff;
  letter-spacing: 0.5px;
  outline: none;
  border: none;
}

form h3 {
  font-size: 32px;
  font-weight: 500;
  line-height: 42px;
  text-align: center;
}

label {
  display: block;
  font-size: 18px;
  font-weight: 500;
}

input[type="text"], input[type="date"] {
  display: block;
  height: 40px;
  width: 100%;
  background-color: rgb(236 1 1 / 20%);
  border-radius: 3px;
  padding: 0 10px;
  margin-top: 8px;
  font-size: 25px;
  font-weight: 300;
}


button {
  margin-top: 50px;
  width: 100%;
  background-color: #ffffff;
  color: #080710;
  padding: 15px 0;
  font-size: 18px;
  font-weight: 600;
  border-radius: 5px;
  cursor: pointer;
}

form.login {
  display: flex;
  flex-direction: column;
  width: 50%;
}

</style>