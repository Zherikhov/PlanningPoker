// Right column with participants avatars
const participants = [
  { id: 1, name: 'Гость', avatar: '🙂' },
  { id: 2, name: 'Гость', avatar: '🙃' },
  { id: 3, name: 'Гость', avatar: '😎' },
]

export default function Participants() {
  return (
    <aside className="bg-gray-800 rounded-lg p-4">
      <h2 className="text-lg mb-4">Участники</h2>
      <div className="flex -space-x-2">
        {participants.map(p => (
          <span
            key={p.id}
            title={p.name}
            className="w-10 h-10 bg-gray-700 rounded-full flex items-center justify-center text-xl border-2 border-gray-800 hover:scale-105 transition"
          >
            {p.avatar}
          </span>
        ))}
      </div>
    </aside>
  )
}
