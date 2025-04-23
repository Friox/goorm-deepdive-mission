export function getDaysDifference(date: string) {
  let startDate = new Date(date)
  startDate.setHours(0, 0, 0, 0)
  let endDate = new Date()
  endDate.setHours(0, 0, 0, 0)
  let diffTime = endDate.getTime() - startDate.getTime()
  let diffDays = diffTime / (1000 * 60 * 60 * 24)
  return diffDays
}