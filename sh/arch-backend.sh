SESSION="BACKEND"
if tmux has-session -t "$SESSION" 2>/dev/null; then
    echo "Session '$SESSION' already running. Attaching..."
else
    tmux new-session -d -s "$SESSION" -c "/home/sriram/git/project/arch-byte/backend/"

    tmux rename-window -t "$SESSION:0" 'CODE'
    tmux send-keys -t "$SESSION:0" 'nvim .' C-m

    tmux new-window -t "$SESSION:1" -n "server" -c "/home/sriram/git/project/arch-byte/backend/"
    tmux send-keys -t "$SESSION:1" 'nvim .' C-m

    tmux new-window -t "$SESSION:2" -n "config" -c "/home/sriram/git/project/arch-byte/backend/"
    tmux send-keys -t "$SESSION:2" 'nvim .' C-m

    tmux new-window -t "$SESSION:3" -n "auth" -c "/home/sriram/git/project/arch-byte/backend/"
    tmux send-keys -t "$SESSION:3" 'nvim .' C-m

    tmux select-window -t "$SESSION:0"

    echo "Backend Session created"
fi


SESSION="DAIRY_SERVER"
if tmux has-session -t "$SESSION" 2>/dev/null; then
    echo "Session '$SESSION' already running. Attaching..."
else
    tmux new-session -d -s "$SESSION" -c "/home/sriram/git/project/arch-byte/backend/"

    tmux rename-window -t "$SESSION:0" 'CODE'

    tmux new-window -t "$SESSION:1" -n "server" -c "/home/sriram/git/project/arch-byte/backend/"

    tmux new-window -t "$SESSION:2" -n "config" -c "/home/sriram/git/project/arch-byte/backend/"

    tmux new-window -t "$SESSION:3" -n "auth" -c "/home/sriram/git/project/arch-byte/backend/"

    tmux select-window -t "$SESSION:0"

    echo "Backend Session created"
fi
