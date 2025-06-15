import { swalError, swalConfirm } from '@/services/swalFireService'

export function useSwalFire() {
  return { swalError, swalConfirm }
}
