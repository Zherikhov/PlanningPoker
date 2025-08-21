import Card from './Card.jsx'

// Central area with active task description and voting deck
export default function Content() {
  const deck = [1, 2, 3, 5, 8, 13, 21, 34, 55, 89]

  return (
    <section className="bg-gray-800 rounded-lg p-4 flex flex-col">
      <div>
        <h2 className="text-xl mb-2">Тема</h2>
        <p className="text-sm text-gray-400 mb-4">Описание</p>
        <p className="text-sm mb-4">Автор: Иванов И.</p>
        <a href="#" className="text-blue-400 hover:underline text-sm">
          Jira link
        </a>
      </div>

      {/* Voting deck */}
      <div className="mt-6 flex-1">
        <div className="grid grid-cols-5 gap-2 mb-4">
          {deck.map(n => (
            <Card key={n} value={n} />
          ))}
        </div>
        <div className="flex flex-wrap gap-2">
          <button className="px-4 py-2 bg-blue-600 hover:bg-blue-500 rounded transition-colors">
            Начать голосование
          </button>
          <button className="px-4 py-2 bg-gray-700 hover:bg-gray-600 rounded transition-colors">
            Показать карты
          </button>
        </div>
      </div>
    </section>
  )
}
