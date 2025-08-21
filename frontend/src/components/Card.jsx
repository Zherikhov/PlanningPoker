// Single card button used in voting deck
export default function Card({ value }) {
  return (
    <button
      className="aspect-square bg-gray-700 rounded flex items-center justify-center text-lg font-semibold hover:bg-gray-600 transition-colors"
      title={`Карта ${value}`}
    >
      {value}
    </button>
  )
}
