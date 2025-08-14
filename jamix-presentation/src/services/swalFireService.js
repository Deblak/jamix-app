import Swal from 'sweetalert2/dist/sweetalert2.js'
import i18n from '@/i18n'
const swalStore = Swal.mixin({
  buttonsStyling: false,
  customClass: {
    confirmButton: 'btn btn-outline-secondary mx-3',
    cancelButton: 'btn btn-dark me-2',
    popup: 'border rounded shadow-sm'
  }
})
/**
 * Displays a confirmation dialog styled with SweetAlert2 + Bootstrap
 * @param {string} title – Title of confirmation
 * @param {string} [text] – Optional description
 * @returns {Promise<SweetAlertResult>}
 */
export function swalConfirm(title, text = '') {
  return swalStore.fire({
    title,
    text,
    showCancelButton: true,
    confirmButtonText: i18n.global.t('ok'),
    cancelButtonText: i18n.global.t('cancel')
  })
}
/**
 * Switch betwwen error messages
 * @param {*} err
 * @returns
 */
export function swalSwitchError(err) {
  const t = i18n.global.t
  const status = err?.response?.status ?? 0
  const code = String(err?.code || '').toUpperCase()

  let title = t('errorUnknownTitle')
  let text = t('errorUnknownText')

  if (status === 0 && code === 'ECONNABORTED') {
    // Timeout (Axios)
    title = t('errorTimeoutTitle')
    text = t('errorTimeoutText')
  } else if (err?.request && !err?.response) {
    // Client 4**
    title = t('errorNetworkTitle')
    text = t('errorNetworkText')
  } else if (status === 400) {
    title = t('errorBadRequestTitle')
    text = t('errorBadRequestText')
  } else if (status === 401) {
    title = t('errorUnauthorizedTitle')
    text = t('errorUnauthorizedText')
  } else if (status === 403) {
    title = t('errorForbiddenTitle')
    text = t('errorForbiddenText')
  } else if (status === 404) {
    title = t('errorNotFoundTitle')
    text = t('errorNotFoundText')
  } else if (status === 409) {
    // Unicity
    title = t('errorConflictTitle')
    text = t('errorConflictText')
  } else if (status === 422) {
    // Validation
    title = t('errorValidationTitle')
    text = t('errorValidationText')
  } else if (status === 429) {
    title = t('errorRateLimitedTitle')
    text = t('errorRateLimitedText')
  } else if (status >= 500) {
    title = t('errorServerTitle')
    text = t('errorServerText')
  }

  return swalStore.fire({
    icon: 'error',
    title,
    text,
    confirmButtonText: t('ok')
  })
}
/**
 * Displays an error alert styled with SweetAlert2 + Bootstrap
 * @param {string} title – Error title
 * @param {string} [text] – Detailed description (optional)
 * @returns {Promise}
 */
export function swalError(title, text = '') {
  return swalStore.fire({ title, text })
}
