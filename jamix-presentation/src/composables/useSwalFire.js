import { swalError, swalConfirm, swalSwitchError } from '@/services/swalFireService'

export function useSwalFire() {
  return { swalError, swalConfirm, swalSwitchError }
}
