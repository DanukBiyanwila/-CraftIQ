import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Swal from 'sweetalert2';
import { useNavigate } from 'react-router-dom';

function Register() {
  const [mode, setMode] = useState('sign-in');
  const navigate = useNavigate();

  // Set default mode on mount
  useEffect(() => {
    setMode('sign-in');
  }, []);

  // Mimic delay for visual mode switch if needed
  useEffect(() => {
    const timer = setTimeout(() => {
      setMode('sign-in');
    }, 200);
    return () => clearTimeout(timer);
  }, []);

  // 💡 One-time refresh to fix CSS conflict
  useEffect(() => {
    const refreshTimer = setTimeout(() => {
      if (performance.navigation.type !== 1) {
        window.location.reload();
      }
    }, 0); 

    return () => clearTimeout(refreshTimer);
  }, []);

  const toggle = () => {
    setMode((prev) => (prev === 'sign-in' ? 'sign-up' : 'sign-in'));
  };

    const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    confirmPassword: ''
    // profilePic: null
  });

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: files ? files[0] : value
    }));
  };

const handleSubmit = async (e) => {
  e.preventDefault();

  if (formData.password !== formData.confirmPassword) {
    return Swal.fire('Error', 'Passwords do not match', 'error');
  }

  // Prepare JSON payload
  const data = {
    username: formData.username,
    email: formData.email,
    password: formData.password,
    // profilePicture: formData.profilePic // This should be a string URL
  };

  try {
    const response = await axios.post('http://localhost:8086/api/user/create', data, {
      headers: {
        'Content-Type': 'application/json'
      }
    });

    Swal.fire('Success', 'Account created successfully!', 'success');
  } catch (error) {
    Swal.fire('Error', error.response?.data?.message || 'Registration failed!', 'error');
  }
};


const handleLogin = async (e) => {
  e.preventDefault();

  const loginData = {
    email: formData.email,
    password: formData.password
  };

  try {
    const response = await axios.post('http://localhost:8086/api/user/login', loginData, {
      headers: {
        'Content-Type': 'application/json'
      }
    });

    // Construct user object from response
    const user = {
      id: response.data.userId,
      email: response.data.email,
      role: response.data.role
    };

    // Save to localStorage
    localStorage.setItem('user', JSON.stringify(user));
    console.log("User data : " , user)

    Swal.fire('Welcome', 'Login successful!', 'success').then(() => {
  navigate('/user/home');

  // Refresh after 1 second
  setTimeout(() => {
    window.location.reload();
  }, 500);
});

  } catch (error) {
    Swal.fire('Login Failed', error.response?.data?.message || 'Invalid credentials', 'error');
  }
};




  return (
    <div>
      <div id="container" className={`container ${mode}`}>
        {/* FORM SECTION */}
        <div className="row">
          {/* SIGN UP */}
        
     <div className="col align-items-center flex-col sign-up">
  <div className="form-wrapper align-items-center">
    <form className="form sign-up" onSubmit={handleSubmit} encType="multipart/form-data">
      <div className="input-group">
        <i className="bx bxs-user" />
        <input type="text" placeholder="Username" name="username" onChange={handleChange} required />
      </div>
      <div className="input-group">
        <i className="bx bx-mail-send" />
        <input type="email" placeholder="Email" name="email" onChange={handleChange} required />
      </div>
      <div className="input-group">
        <i className="bx bxs-profile" />
        <input type="file" name="profilePic" onChange={handleChange} accept="image/*" required />
      </div>
      <div className="input-group">
        <i className="bx bxs-lock-alt" />
        <input type="password" placeholder="Password" name="password" onChange={handleChange} required />
      </div>
      <div className="input-group">
        <i className="bx bxs-lock-alt" />
        <input type="password" placeholder="Confirm Password" name="confirmPassword" onChange={handleChange} required />
      </div>
      <button type="submit">Sign up</button>
      <p>
        <span>Already have an account?</span>
        <b onClick={toggle} className="pointer">Sign in here</b>
      </p>
    </form>
  </div>
</div>


          {/* END SIGN UP */}

          {/* SIGN IN */}
          <div className="col align-items-center flex-col sign-in">
            <div className="form-wrapper align-items-center">
              <div className="form sign-in">
               <div className="input-group">
  <i className="bx bxs-user" />
  <input
    type="email"
    placeholder="Email"
    name="email"
    onChange={handleChange}
    required
  />
</div>
<div className="input-group">
  <i className="bx bxs-lock-alt" />
  <input
    type="password"
    placeholder="Password"
    name="password"
    onChange={handleChange}
    required
  />
</div>
<button onClick={handleLogin}>Sign in</button>

                <p><b>Forgot password?</b></p>
                <p>
                  <span>Don't have an account?</span>
                  <b onClick={toggle} className="pointer">Sign up here</b>
                </p>
              </div>
            </div>
          </div>
          {/* END SIGN IN */}
        </div>

        {/* CONTENT SECTION */}
        <div className="row content-row">
          <div className="col align-items-center flex-col">
            <div className="text sign-in">
              <h2>Welcome</h2>
            </div>
            <div className="img sign-in" />
          </div>
          <div className="col align-items-center flex-col">
            <div className="img sign-up" />
            <div className="text sign-up">
              <h2>Join with us</h2>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Register;
