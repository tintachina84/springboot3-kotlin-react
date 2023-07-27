import { render, screen, fireEvent } from '@testing-library/react';
import App from './App';
import TestRenderer from 'react-test-renderer';
import AddCar from './components/AddCar';

// test('renders learn react link', () => {
//   render(<App />);
//   const linkElement = screen.getByText(/learn react/i);
//   expect(linkElement).toBeInTheDocument();
// });

test('Can open add car dialog', () => {
  render(<App />);
  fireEvent.click(screen.getByText(/new car/i));
  expect(screen.getByRole('dialog')).toBeInTheDocument();
  expect(screen.getByRole('dialog')).toHaveTextContent('New car');
});

test('renders a snapshot', () => {
  const tree = TestRenderer.create(<AddCar />).toJSON();
  expect(tree).toMatchSnapshot();
});
