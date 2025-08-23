const emailRegex = /^(?:[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*|"(?:["]|\\")+")@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$/

export function validateRegister({ name, email, password }) {
  const errors = {}
  if (!name || name.trim().length < 2 || name.trim().length > 60) {
    errors.name = 'invalid'
  }
  if (!email || !emailRegex.test(email)) {
    errors.email = 'invalid'
  }
  if (!password || password.length < 8) {
    errors.password = 'invalid'
  }
  return errors
}
