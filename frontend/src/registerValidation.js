const emailRegex = /^(?:[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*|"(?:["]|\\")+")@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$/

export function validateRegister({ name, email, password }) {
  const errors = {}
  const trimmedName = name ? name.trim() : ''
  const trimmedEmail = email ? email.trim().toLowerCase() : ''
  if (!trimmedName || trimmedName.length < 2 || trimmedName.length > 60) {
    errors.name = 'invalid'
  }
  if (!trimmedEmail || !emailRegex.test(trimmedEmail)) {
    errors.email = 'invalid'
  }
  if (!password || password.length < 8) {
    errors.password = 'invalid'
  }
  return errors
}
