import React from 'react';
import {
  BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend,
  PieChart, Pie, Cell
} from 'recharts';

// Shared data
const data = [
  { name: 'React', students: 400 },
  { name: 'Java', students: 300 },
  { name: 'Python', students: 200 },
  { name: 'C++', students: 278 },
];

// Pie chart colors
const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

function ProgressView() {
  return (
    <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'space-around', padding: '20px' }}>
      
      {/* Bar Chart */}
      <div>
        <h3>Bar Chart - Student Count by Course</h3>
        <BarChart width={500} height={400} data={data}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="name" />
          <YAxis />
          <Tooltip />
          <Legend />
          <Bar dataKey="students" fill="#8884d8" />
        </BarChart>
      </div>

      {/* Pie Chart */}
      <div>
        <h3>Pie Chart - Student Distribution</h3>
        <PieChart width={500} height={400}>
          <Pie
            data={data}
            dataKey="students"
            nameKey="name"
            cx="50%"
            cy="50%"
            outerRadius={120}
            fill="#8884d8"
            label
          >
            {data.map((entry, index) => (
              <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip />
        </PieChart>
      </div>

    </div>
  );
}

export default ProgressView;
